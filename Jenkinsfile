pipeline {
    agent any
    tools { 
    //nom du maven sur Jenkins = mavenSelenium ici
      maven 'mavenSelenium' 
      //nom du JAVA_HOME sur Jenkins = jdkSelenium ici
      jdk 'jdkSelenium' 
    }
 
    stages {
        stage('Test') {
            steps {
                bat "mvn -D clean test -Dtest=*/example/*"
                bat "mvn surefire-report:report"
            }
 
            post {                
                always {
                   publishHTML([
                       allowMissing: false, 
                       alwaysLinkToLastBuild: false, 
                       keepAll: false, 
                       reportDir: './target/site', 
                       reportFiles: 'surefire-report.html', 
                       reportName: 'HTML Report', 
                       reportTitles: '', 
                       useWrapperFileDirectly: true])
                }
            }
        }
    }
}