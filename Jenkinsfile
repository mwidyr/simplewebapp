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

                withCredentials([usernamePassword(credentialsId: 'efdbce7e-d275-4d57-b8cc-2a2e77556a88',
                                 usernameVariable: 'mwidyr',
                                 passwordVariable: 'tokopedia789')]){
                    sh("git clone https://github.com/mwidyr/simplewebapp.git")
                    sh("git pull")
                    sh("git checkout widy_1")
                    sh("git branch")
                }
            }
        }

        stage ('Checkout check branch') {
            steps {
                sh 'branch'
            }
        }

        stage ('change lalaa') {
            steps{
                sh 'pull'
                sh 'branch'
                script {
                try{
                    sh 'checkout '+params.pr_number
                } catch (err){
                    echo 'fail'
                    currentBuild.result = 'ABORTED'
                    error('Stopping Checkout stage…')
                }
                }
                sh 'branch'
            }
        }

        stage ('Get Dependency') {
            steps {
                sh 'echo get dependency'
            }
        }

        stage ('Build Binary Test') {
            steps {
            script {
                try{
                    sh 'mvn clean compile'
                } catch (err){
                    echo 'fail'
                    currentBuild.result = 'ABORTED'
                    error('Stopping Build Binary Test stage…')
                }
                }
            }
        }

        stage ('Initiate Test') {
            steps {
            script {
                try{
                    sh 'mvn test'
                } catch (err){
                    echo 'fail'
                    currentBuild.result = 'ABORTED'
                    error('Stopping Initiate Test stage…')
                }
                }
            }
        }


        stage ('Merge') {
            steps {
            script {
                try{
                    sh 'git branch'
                    sh 'git checkout master'
                    sh 'git branch'
                    sh 'git merge '+param.pr_number
                    sh 'git push origin master'
                } catch (err){
                    echo 'fail'
                    currentBuild.result = 'ABORTED'
                    error('Stopping Merge stage…')
                }
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