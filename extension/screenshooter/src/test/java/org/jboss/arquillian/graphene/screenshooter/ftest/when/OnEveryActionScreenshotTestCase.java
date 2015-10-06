/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.arquillian.graphene.screenshooter.ftest.when;

import java.util.ArrayList;
import java.util.List;

import org.arquillian.extension.recorder.When;
import org.arquillian.extension.recorder.screenshooter.api.Screenshot;
import org.jboss.arquillian.graphene.findby.ByJQuery;
import org.jboss.arquillian.graphene.screenshooter.ftest.when.util.AbstractTestClass;
import org.jboss.arquillian.graphene.screenshooter.ftest.when.util.ArquillianXmlUtil;
import org.jboss.arquillian.graphene.screenshooter.ftest.when.util.DefaultTestClass;
import org.jboss.arquillian.graphene.screenshooter.ftest.when.util.ValidationUtil;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Test if the {@code takeOnEveryAction} property works correctly and if the associate screenshots are (not) taken it the
 * right moments
 *
 * @author <a href="mailto:mjobanek@redhat.com">Matous Jobanek</a>
 */
public class OnEveryActionScreenshotTestCase extends AbstractScreenshotTestCase {

    /**
     * Should take only a screenshot when the page is loaded ({@link WebDriver#get(String)} operation)
     */
    @Test
    public void onEveryActionDefaultTest() {
        ArquillianXmlUtil.setProperties(When.ON_EVERY_ACTION);
        runTest(DefaultTestClass.class);

        List<String> fileNames = getFilesNameList("get0");
        ValidationUtil.verifyScreenshotPresence(DefaultTestClass.class, fileNames);
    }

    /**
     * Should take three screenshots when the page is loaded (caled {@link WebDriver#get(String)} operation three times)
     */
    @Test
    public void onEveryActionWithAnnotationTest() {
        ArquillianXmlUtil.setProperties(When.ON_EVERY_ACTION);
        runTest(OnEveryActionWithAnnotationTestClass.class);

        List<String> fileNames = getFilesNameList("get0", "get1", "get2");
        ValidationUtil.verifyScreenshotPresence(OnEveryActionWithAnnotationTestClass.class, fileNames);
    }

    /**
     * Should take only a screenshot when the page is loaded ({@link WebDriver#get(String)} operation) no matter this
     * test fails
     */
    @Test
    public void onEveryActionFailingTest() {
        ArquillianXmlUtil.setProperties(When.ON_EVERY_ACTION);
        runFailingTest(FailingTestClass.class);

        List<String> fileNames = getFilesNameList("get0");
        ValidationUtil.verifyScreenshotPresence(FailingTestClass.class, fileNames);
    }

    /**
     * Should take several screenshots corresponding to the actions called on the {@link WebDriver}
     */
    @Test
    public void onEveryActionManyActionsTest() {
        ArquillianXmlUtil.setProperties(When.ON_EVERY_ACTION);
        runTest(OnEveryActionManyActionsTestClass.class);

        List<String> fileNames =
            getFilesNameList("get0", "findElement1", "findElements2", "getCurrentUrl3", "getWindowHandle4", "navigate5",
                "manage6", "getTitle7", "getPageSource8", "getWindowHandles9", "switchTo10");
        ValidationUtil.verifyScreenshotPresence(OnEveryActionManyActionsTestClass.class, fileNames);
    }

    private List<String> getFilesNameList(String... operationNames) {
        List<String> fileNames = new ArrayList<String>(operationNames.length);
        for (String opName : operationNames) {
            fileNames.add(opName + "_" + When.ON_EVERY_ACTION);
        }
        return fileNames;
    }

    public static class OnEveryActionWithAnnotationTestClass extends AbstractTestClass {

        @Override
        @Test
        @Screenshot(takeOnEveryAction = true)
        public void testMethod() {
            loadPage();
            loadPage();
            loadPage();
        }
    }

    public static class FailingTestClass extends AbstractTestClass {

        @Override public void testMethod() {
            loadPage();
            Assert.fail();
        }
    }

    public static class OnEveryActionManyActionsTestClass extends AbstractTestClass {

        @Override
        public void testMethod() {
            loadPage();

            getBrowser().findElement(By.id("root"));
            getBrowser().findElements(ByJQuery.selector("select>option"));
            getBrowser().getCurrentUrl();
            getBrowser().getWindowHandle();
            getBrowser().navigate();
            getBrowser().manage();
            getBrowser().getTitle();
            getBrowser().getPageSource();
            getBrowser().getWindowHandles();
            getBrowser().switchTo();
            getBrowser().close();
            getBrowser().quit();
        }
    }
}
