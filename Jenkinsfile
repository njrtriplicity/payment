pipeline {
    agent any
    environment {
        paymentImage = ''
    }
    tools {
        maven 'maven-3.8.1'
        jdk 'jdk8'
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                    echo docker info
                '''
            }
        }
        stage ('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage ('BuildDocker') {
            steps {
                   sh 'docker build --tag=njrtriplicity/payment:latest .'
            }
        }
        stage('Upload Image to DockerHub'){
            steps {
//                 echo "cred = ${git}"
                withCredentials([usernameColonPassword(credentialsId: 'git', variable: 'docker-hub')]) {
                    sh "docker login -u njrtriplicity -p cb@viooh3"
                }
                  sh 'docker push njrtriplicity/payment:latest'
            }
        }
    }
}