package com.github.cric.common.service.cripapi;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.cric.common.exception.InvalidRemoteResponseException;
import com.github.cric.common.model.Match;
import com.github.cric.common.model.Team;

@Service
@Lazy
class CripApiRemoteService {

    private static final Logger LOG = LoggerFactory.getLogger(CripApiRemoteService.class);

    private final RestTemplate restTemplate;
    private final CricApiUrlConfig urlConfig;
    private final ObjectMapper mapper;

    CripApiRemoteService(RestTemplate restTemplate, CricApiUrlConfig urlConfig, ObjectMapper mapper) {

        this.restTemplate = restTemplate;
        this.urlConfig = urlConfig;
        this.mapper = mapper;
    }

    /**
     * Method to fetch current matches ongoing or some future matches. This
     * method returns matches in sorted order of match date / time.
     * 
     * @return
     */
    List<Match> getCurrentMatches() {

        String matchesAsString = restTemplate.getForObject(urlConfig.getMatchesApi(), JsonNode.class).get("matches")
                .asText();
        try {
            List<RawMatch> rawMatches = mapper.readValue(matchesAsString, new TypeReference<List<RawMatch>>(){});

            return rawMatches.stream()
                    .filter(this::filterUnknownTeam)
                    .sorted((r1, r2) -> r1.getDate().compareTo(r2.getDate()))
                    .map(this::mapToMatch)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new InvalidRemoteResponseException(matchesAsString, e);
        }
    }

    private Match mapToMatch(RawMatch rawMatch) {

        return new Match()
                .setMatchId(rawMatch.getMatchId())
                .setMatchStarted(rawMatch.isMatchStarted())
                .setFirstTeam(Team.fromName(rawMatch.getFirstTeam()))
                .setSecondTeam(Team.fromName(rawMatch.getSecondTeam()));
    }

    /**
     * Method to filter unknown team names. Only recognized team names are in
     * {@link Team} enum.
     * 
     * @param rawMatch
     * @return
     */
    private boolean filterUnknownTeam(RawMatch rawMatch) {

        if (Team.fromName(rawMatch.getFirstTeam()) == null) {

            LOG.warn("unknown team name found {}", rawMatch.getFirstTeam());
            return false;
        }

        if (Team.fromName(rawMatch.getSecondTeam()) == null) {

            LOG.warn("unknown team name found {}", rawMatch.getSecondTeam());
            return false;
        }
        return true;
    }
}