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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.drools.ruleunits.api.RuleUnit;
import org.drools.ruleunits.api.RuleUnitInstance;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/find-not-approved-id-and-amount")
public class LoanUnitQueryFindNotApprovedIdAndAmountEndpoint {

    @Autowired
    RuleUnit<org.kie.kogito.queries.LoanUnit> ruleUnit;

    public LoanUnitQueryFindNotApprovedIdAndAmountEndpoint() {
    }

    public LoanUnitQueryFindNotApprovedIdAndAmountEndpoint(RuleUnit<org.kie.kogito.queries.LoanUnit> ruleUnit) {
        this.ruleUnit = ruleUnit;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<LoanUnitQueryFindNotApprovedIdAndAmount.Result> executeQuery(@RequestBody(required = true) org.kie.kogito.queries.LoanUnit unitDTO) {
        RuleUnitInstance<org.kie.kogito.queries.LoanUnit> instance = ruleUnit.createInstance(unitDTO);
        // Do not return the result directly to allow post execution codegen (like monitoring)
        List<LoanUnitQueryFindNotApprovedIdAndAmount.Result> response = LoanUnitQueryFindNotApprovedIdAndAmount.execute(instance);
        instance.close();
        return response;
    }

    @PostMapping(value = "/first", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public LoanUnitQueryFindNotApprovedIdAndAmount.Result executeQueryFirst(@RequestBody(required = true) org.kie.kogito.queries.LoanUnit unitDTO) {
        List<LoanUnitQueryFindNotApprovedIdAndAmount.Result> results = executeQuery(unitDTO);
        LoanUnitQueryFindNotApprovedIdAndAmount.Result response = results.isEmpty() ? null : results.get(0);
        return response;
    }
}
