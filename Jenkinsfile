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

        stage ('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage ('Testing Stage') {
            steps {
                sh 'mvn test'
            }
        }

        stage ('Deploying stage') {
            steps {
                sh 'mvn clean install'
            }
        }
    }
}