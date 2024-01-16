pipeline {
    agent any

    environment {
        // Define your Docker Hub credentials
        DOCKER_HUB_CREDENTIALS = credentials('ngnawens@gmail.com')
        DOCKER_IMAGE_NAME = 'ngnawen/spring-boot-chatgpt'
        MAVEN_HOME = tool 'Maven' // Assumes 'Maven' is configured in Jenkins as a Tool
    }

    stages {
        stage('Checkout') {
            steps {
                // Check out the source code from your version control system
                checkout scm
            }
        }

        stage('Build with Maven') {
            steps {
                // Run Maven build
                sh "${MAVEN_HOME}/bin/mvn clean install"
            }
        }

        stage('Build and Push Docker Image') {
            steps {
                script {
                    // Build the Docker image
                    dockerImage = docker.build(env.DOCKER_IMAGE_NAME, '-f path/to/Dockerfile .')

                    // Log in to Docker Hub
                    docker.withRegistry('https://registry.hub.docker.com', env.DOCKER_HUB_CREDENTIALS) {
                        // Push the Docker image to Docker Hub
                        dockerImage.push()
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'Maven build and Docker image push to Docker Hub succeeded!'
        }
    }
}
