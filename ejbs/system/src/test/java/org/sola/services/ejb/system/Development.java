/**
 * ******************************************************************************************
 * Copyright (C) 2014 - Food and Agriculture Organization of the United Nations (FAO).
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice,this list
 *       of conditions and the following disclaimer.
 *    2. Redistributions in binary form must reproduce the above copyright notice,this list
 *       of conditions and the following disclaimer in the documentation and/or other
 *       materials provided with the distribution.
 *    3. Neither the name of FAO nor the names of its contributors may be used to endorse or
 *       promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT
 * SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,STRICT LIABILITY,OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * *********************************************************************************************
 */
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package org.sola.services.ejb.system;

import javax.transaction.Status;
import javax.transaction.UserTransaction;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.sola.services.common.test.AbstractEJBTest;
import org.sola.services.ejb.system.br.Result;
import org.sola.services.ejb.system.br.ResultFeedback;
import org.sola.services.ejb.system.businesslogic.SystemEJB;
import org.sola.services.ejb.system.businesslogic.SystemEJBLocal;
import org.sola.services.ejb.system.repository.entities.Br;
import org.sola.services.ejb.system.repository.entities.BrValidation;
import static org.junit.Assert.*;
import org.sola.services.common.br.ValidationResult;

/**
 *
 * @author manoku
 */
public class Development extends AbstractEJBTest {

    private static final String LOGIN_USER = "test";
    private static final String LOGIN_PASS = "test";

    public Development() {
        super();
    }

    @Before
    public void setUp() throws Exception {
        login(LOGIN_USER, LOGIN_PASS);
    }

    @After
    public void tearDown() throws Exception {
        logout();
    }


    /**
     * Test of getBr.
     */
    @Test
//    @Ignore
    public void test() throws Exception {
        SystemEJBLocal instance = (SystemEJBLocal) getEJBInstance(SystemEJB.class.getSimpleName());

        System.out.println("getBrForValidatingBulkOperations");
        List<BrValidation> result11 = 
                instance.getBrForValidatingTransaction("bulkOperationSpatial", "pending", null);
        HashMap<String, Serializable> params = new HashMap<String, Serializable>();

        //The business rules fired, are supposed to get only one parameter and that is
        // the id of the transaction
        params.put("id", "6a5faa7a-9c12-49ec-9f20-b4b8a86e583f");

        //Run the validation
        List<ValidationResult> validationResultList =
                instance.checkRulesGetValidation(result11, "en", params);

    }

    private void printResult(List<BrValidation> result) {
        System.out.println("Found: " + (result == null ? "!None!" : result.size()));
    }
}
