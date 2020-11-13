pipeline {
    agent any
    tools {
        maven 'maven_home'
        jdk 'JDK1.8.0_231'
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

        stage ('Initialize Param') {
            steps {
                sh 'echo ' + params
                sh 'echo ' + params.pr_number
            }
        }

        stage ('Build Testing') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage ('Testing Stage') {
            steps {
                sh 'mvn test'
            }
        }

        stage ('Build Binary') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage ('Deploying all') {
            steps {
                sh '''
                ls ~/.jenkins/workspace/jenkins-pipeline-example/target
                cp ~/.jenkins/workspace/jenkins-pipeline-example/target/simplewebapp-webapp.jar ~/app/web/java/simplewebapp-filejar
                '''
            }
        }
    }
}