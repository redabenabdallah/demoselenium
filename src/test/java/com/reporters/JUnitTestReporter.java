package com.reporters;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class JUnitTestReporter {

    static File junitReport;
    static BufferedWriter junitWriter;

    @BeforeClass
    public static void initReport() throws IOException {

        String junitReportFile = System.getProperty("user.dir")
                + "\\junitReportFile.html";
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        Date date = new Date();
        junitReport = new File(junitReportFile);
        junitWriter = new BufferedWriter(new FileWriter(junitReport));
        junitWriter.write("<html><body>");
        junitWriter.write("<h1>Bilan des tests - " + dateFormat.format(date)
                + "</h1>");
        junitWriter.write("<br/>");
        junitWriter.write("<table><thead><tr><th>Titre du test</th><th>Statut du test</th></tr></thead><tbody>");

    }

    @AfterClass
    public static void closeReport() throws IOException {

        junitWriter.write("</tbody></table></body></html>");
        junitWriter.close();
        Desktop.getDesktop().browse(junitReport.toURI());

    }

    @Rule
    public TestRule watchman = new TestWatcher() {

        @Override
        public Statement apply(Statement base, Description description) {
            return super.apply(base, description);
        }

        @Override
        protected void succeeded(Description description) {
            try {
                junitWriter.write("<tr><td>"  + description.getDisplayName()+ "</td><td style=\"color:green;font-weight:bold\">" + " "
                        + "success!" + "</td></tr>");
                ;
            } catch (Exception e1) {
                System.out.println(e1.getMessage());
            }
        }

        @Override
        protected void failed(Throwable e, Description description) {
            try {
                junitWriter.write("<tr><td> " + description.getDisplayName() +  "</td><td style=\"color:red;font-weight:bold\">" 
                        + e.getClass().getSimpleName() + "</td></tr>");
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }
    };
}