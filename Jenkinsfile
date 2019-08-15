pipeline {

    agent any
    

    stages {
        stage ('Unit Tests') {
           steps {
                echo '------------> Unit Test <------------'
                sh './gradlew clean test'
           }
        }   

        stage ('SonarCloud Static Code Analysis') {
             steps{
                echo '------------> Static code analysis <------------'
                withSonarQubeEnv('sonarQube') {
                    sh './gradlew sonarqube'
                }
            }
        }

        stage ('SonarCloud Quality Gate') { 
            steps {
                echo "------------>Static code analysis <------------"
                withSonarQubeEnv('sonarQube') {
                    timeout(time: 5, unit: 'MINUTES') {
                        waitForQualityGate abortPipeline: true
                    }
                }
            }
        }

        stage('Build') {
            steps {
                sh './gradlew assemble'
                archiveArtifacts( artifacts: 'build/libs/*.jar', fingerprint: true)
            }
        }
    }
}