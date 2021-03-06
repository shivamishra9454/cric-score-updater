/**
 *   Copyright 2017 Pratapi Hemant Patel
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *   
 */
package com.github.cric.common.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SummaryScore {

    private int matchId;
    private boolean matchStarted;
    private Team firstTeam;
    private Team secondTeam;
    private String matchType;

    /*
     * impl-note
     * 
     * Need improvement here to parse score more friendly
     */
    private String score;
    private String inningRequirement;

    public int getMatchId() {

        return matchId;
    }

    public boolean isMatchStarted() {

        return matchStarted;
    }

    public Team getFirstTeam() {

        return firstTeam;
    }

    public Team getSecondTeam() {

        return secondTeam;
    }

    public String getMatchType() {

        return matchType;
    }

    public String getScore() {

        return score;
    }

    public String getInningRequirement() {

        return inningRequirement;
    }

    public SummaryScore setMatchId(int matchId) {

        this.matchId = matchId;
        return this;
    }

    public SummaryScore setMatchStarted(boolean matchStarted) {

        this.matchStarted = matchStarted;
        return this;
    }

    public SummaryScore setFirstTeam(Team firstTeam) {

        this.firstTeam = firstTeam;
        return this;
    }

    public SummaryScore setSecondTeam(Team secondTeam) {

        this.secondTeam = secondTeam;
        return this;
    }

    public SummaryScore setMatchType(String matchType) {

        this.matchType = matchType;
        return this;
    }

    public SummaryScore setScore(String score) {

        this.score = score;
        return this;
    }

    public SummaryScore setInningRequirement(String inningRequirement) {

        this.inningRequirement = inningRequirement;
        return this;
    }

    @Override
    public String toString() {

        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("matchId", matchId)
                .append("matchStarted", matchStarted)
                .append("firstTeam", firstTeam)
                .append("secondTeam", secondTeam)
                .append("matchType", matchType)
                .append("score", score)
                .append("inningRequirement", inningRequirement)
                .build();
    }
}
