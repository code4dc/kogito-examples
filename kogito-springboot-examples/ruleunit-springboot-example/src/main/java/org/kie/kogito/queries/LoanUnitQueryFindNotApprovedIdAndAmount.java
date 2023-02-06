/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.kogito.queries;

import java.util.List;
import java.util.Map;

import org.drools.ruleunits.api.RuleUnitInstance;

import static java.util.stream.Collectors.toList;

public class LoanUnitQueryFindNotApprovedIdAndAmount {

    public static List<LoanUnitQueryFindNotApprovedIdAndAmount.Result> execute(RuleUnitInstance<org.kie.kogito.queries.LoanUnit> instance) {
        return instance.executeQuery("FindNotApprovedIdAndAmount").toList().stream().map(LoanUnitQueryFindNotApprovedIdAndAmount::toResult).collect(toList());
    }

    private static LoanUnitQueryFindNotApprovedIdAndAmount.Result toResult(Map<String, Object> tuple) {
        return new Result((Integer) tuple.get("$amount"), (java.lang.String) tuple.get("$id"));
    }

    public static class Result {

        public Result(int $amount, String $id) {
            this.$amount = $amount;
            this.$id = $id;
        }

        private final int $amount;

        public int get$amount() {
            return $amount;
        }

        private final String $id;

        public String get$id() {
            return $id;
        }
    }
}
