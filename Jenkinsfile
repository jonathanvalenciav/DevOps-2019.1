pipeline {

    agent any
    

    stages {
        stage ('Unit Tests') {
            echo '------------>Unit Test<------------'
            sh './gradlew --stacktrace test'
            junit '**/build/test-results/*.xml'
            jacoco classPattern: '**/build/classes/java', execPattern: '**/jacoco/test.exec', sourcePattern: '**/src/main/java'
        }   

        stage ('SonarCloud Static Code Analysis') {
             steps{
                echo '------------>Static code analysis<------------'
                withSonarQubeEnv('sonarQube') {
                    sh './gradlew sonarqube'
                }
            }
        }

        stage ('SonarCloud Quality Gate') { 
            steps {
                echo "------------>Static code analysis<------------"
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