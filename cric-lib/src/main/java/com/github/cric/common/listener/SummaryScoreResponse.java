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
package com.github.cric.common.listener;

import com.github.cric.common.model.SummaryScore;

public class SummaryScoreResponse {


    private SummaryScore summaryScore;
    private Exception e;
    
    public SummaryScoreResponse(SummaryScore summaryScore) {
        this.summaryScore = summaryScore;
    }
    
    public SummaryScoreResponse(Exception e) {
        this.e = e;
    }
    
    public boolean hasError() {
        return null != e;
    }
    
    public SummaryScore getSummaryScore() {
        return summaryScore;
    }
    
    public Exception getError() {
        return e;
    }
}