pipeline {

    agent any
    

    stages {
           stage ('Unit Tests') {
                parallel {
                    stage('Test'){
                        steps {
                            echo '------------>test<------------'
                            sh './gradlew --stacktrace test'
                        }
                    }
                }
            }   

        stage ('SonarCloud Static Code Analysis') {
             steps{
                echo '------------>Static code analysis<------------'
                withSonarQubeEnv('sonarQube') {
                    sh "${tool name: 'SonarScanner', type: 'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner"
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