pipeline {
    agent any

    environment {
        GITHUB_CREDENTIALS_ID = 'islem_github'
    }

    tools {
        maven 'Maven' // Utilisez la version de Maven configurée
    }

    stages {
        stage('Getting Project from Git ') {
            steps {
                script {
                    echo "Checking out the repository..."
                    git url: 'https://github.com/allouchyrihem/5SAE6-G2-kaddem.git', branch: 'islem-abderahmen', credentialsId: "${env.GITHUB_CREDENTIALS_ID}"
                }
            }
        }
        stage('Cleaning the project') {
            steps {
                script {
                    echo 'Cleaning the project...'
                    withMaven(maven: 'Maven') {
                        sh 'mvn clean'
                    }
                }
            }
        }

        stage('Artifact construction') {
            steps {
                script {
                    echo 'Building the project and packaging the artifact...'
                    withMaven(maven: 'Maven') {
                        sh 'mvn package' // Modifiez cette commande si nécessaire
                    }
                }
            }
        }

        stage('Unit Tests') {
            steps {
                script {
                    echo 'Running unit tests...'
                    withMaven(maven: 'Maven') {
                        sh 'mvn test' // Exécute les tests
                    }
                }
            }
        }

    }
     post {
        success {
            echo 'Pipeline executed successfully! The deliverable is available in the target directory.'
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
        failure {
            echo 'Pipeline failed. Check the logs for more information.'
        }
    }

       
}