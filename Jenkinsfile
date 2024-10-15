pipeline {
    agent any

    environment {
        GITHUB_CREDENTIALS_ID = 'islem_github'
        DOCKERHUB_CREDENTIALS_ID = 'dockerhub_credentials_islem' // Ajoutez vos identifiants DockerHub ici
    }

    tools {
        maven 'Maven' // Utilisez la version de Maven configurée dans Jenkins
    }

    stages {
        stage('Getting Project from Git') {
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

        stage('Building Docker Image') {
            steps {
                script {
                    echo 'Building Docker image...'
                    // Remplacez  par le nom correct de l'image
                    sh 'docker build -t islem997/islemabderahmen_g2_kaddem:v1.0.0 .'
                }
            }
        }

        stage('Pushing Docker Image to DockerHub') {
            steps {
                script {
                    echo 'Pushing Docker image to DockerHub...'
                    withCredentials([usernamePassword(credentialsId: "${env.DOCKERHUB_CREDENTIALS_ID}", usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        sh 'docker login -u $DOCKER_USER -p $DOCKER_PASS'
                        sh 'docker push islem997/islemabderahmen_g2_kaddem:v1.0.0'
                    }
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                script {
                    echo 'Deploying application using Docker Compose...'
                    sh 'docker-compose up -d'
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
