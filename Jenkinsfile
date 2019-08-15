pipeline {

    agent any
    

    stages {
                stage('Checkout'){
            steps{
                echo "------------>Checkout<------------"
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], 
                gitTool: 'Git_Centos', submoduleCfg: [], userRemoteConfigs: [[credentialsId:'GitHub_jonathanavalencia', 
                url:'https://github.com/jonathanavalencia/cparking.git']]])
            }
        }
s


        stage ('Unit Tests') {
            steps{
                echo "------------>Unit tests<------------"
                sh 'gradle test'
                junit '**/jacoco/test-results/*.xml'
                jacoco classPattern: '**/build/classes/java', execPattern: '**/jacoco/test.exec', sourcePattern: '**/src/main/java'
            }
        }

        stage ('SonarCloud Static Code Analysis') {
            //Inserte su declaracion aqui
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