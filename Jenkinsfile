pipeline {
    agent any

    environment {
        GITHUB_CREDENTIALS_ID = 'islem_github'
        DOCKERHUB_CREDENTIALS_ID = 'dockerhub_credentials_islem' // Ajoutez vos identifiants DockerHub ici
        EMAIL_RECIPIENT = 'abderahmenislem052@gmail.com'
                EMAIL_SUBJECT = 'Statut du Build Jenkins'
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
              always {
                  script {
                      def jobName = env.JOB_NAME
                      def buildNumber = env.BUILD_NUMBER
                      def pipelineStatus = currentBuild.result ?: 'UNKNOWN'
                      def bannerColor = pipelineStatus.toUpperCase() == 'SUCCESS' ? 'green' : 'red'

                      def body = """<html>
                      <body>
                          <div style="border: 4px solid ${bannerColor}; padding: 10px;">
                              <h2>${jobName} - Build ${buildNumber}</h2>
                              <div style="background-color: ${bannerColor}; padding: 10px;">
                                  <h3 style="color: white;">Pipeline Status: ${pipelineStatus.toUpperCase()}</h3>
                              </div>
                              <p>Check the <a href="${BUILD_URL}">console output</a>.</p>
                          </div>
                      </body>
                      </html>"""

                      emailext (
                          subject: "${jobName} - Build ${buildNumber} - ${pipelineStatus.toUpperCase()}",
                          body: body,
                          to: 'abderahmenislem052@gmail.com',
                          from: 'jenkins@example.com',
                          replyTo: 'jenkins@example.com',
                          mimeType: 'text/html',

                      )
                  }
              }
          }
}
