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
                bat "mvn -D clean test"
            }
 
            post {                
                success {
                   publishHTML([
                       allowMissing: false, 
                       alwaysLinkToLastBuild: false, 
                       keepAll: false, 
                       reportDir: '.', 
                       reportFiles: 'junitReportFile.html', 
                       reportName: 'HTML Report', 
                       reportTitles: '', 
                       useWrapperFileDirectly: true])
                }
            }
        }
    }
}