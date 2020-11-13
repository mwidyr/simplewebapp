pipeline {
    agent any
    tools {
        maven 'maven_home'
        jdk 'JDK1.8.0_231'
    }
    stages {
        stage ('Details') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }
        stage ('Initialize Config') {
            steps {
                sh 'echo pr number or branch name ' + params.pr_number+ ' t'
            }
        }

        stage ('Validation & PR Checking') {
            steps {
                sh 'echo need_to_validate_first'
            }
        }

        stage ('Checkout') {
            steps {
                sh 'git pull'
                sh 'git branch'
                sh 'git checkout '+params.pr_number
                sh 'git branch'
                failure {
                    echo 'This will run only if failed'
                    currentBuild.result = 'ABORTED'
                    error('Stopping Checkout stage…')
                }
            }
        }

        stage ('Get Dependency') {
            steps {
                sh 'echo get dependency'
            }
        }

        stage ('Build Binary Test') {
            steps {
                sh 'mvn clean compile'
                failure {
                    echo 'This will run only if failed'
                    currentBuild.result = 'ABORTED'
                    error('Stopping Build Binary Test stage…')
                }
            }
        }

        stage ('Initiate Test') {
            steps {
                sh 'mvn test'
                failure {
                    echo 'This will run only if failed'
                    currentBuild.result = 'ABORTED'
                    error('Stopping Initiate Test stage…')
                }
            }
        }


        stage ('Merge') {
            steps {
                sh 'git branch'
                sh 'git checkout master'
                sh 'git branch'
                sh 'git merge '+param.pr_number
                sh 'git push origin master'
                failure {
                    echo 'This will run only if failed'
                    currentBuild.result = 'ABORTED'
                    error('Stopping Merge stage…')
                }
            }
        }


        stage ('Build Package') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage ('Deploying All') {
            steps {
                sh '''
                ls ~/.jenkins/workspace/jenkins-pipeline-example/target
                cp ~/.jenkins/workspace/jenkins-pipeline-example/target/simplewebapp-webapp.jar ~/app/web/java/simplewebapp-filejar
                '''
            }
        }
    }
}