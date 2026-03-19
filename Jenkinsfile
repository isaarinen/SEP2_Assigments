pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean verify'
            }
        }

        stage('Code Coverage') {
            steps {
                recordCoverage(
                    tools: [[parser: 'JACOCO', pattern: '**/target/site/jacoco/jacoco.xml']],
                    sourceCodeRetention: 'LAST_BUILD',
                    qualityGates: [
                        [threshold: 40.0, metric: 'INSTRUCTION', baseline: 'PROJECT', criticality: 'FAILURE'],
                        [threshold: 40.0, metric: 'BRANCH', baseline: 'PROJECT', criticality: 'FAILURE']
                    ]
                )
            }
        }
        stage('Deploy Docker Image') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: "${DOCKERHUB_CREDENTIALS}",
                    usernameVariable: 'DOCKERHUB_USER',
                    passwordVariable: 'DOCKERHUB_PASS'
                )]) {
                    sh '''
                        echo "$DOCKERHUB_PASS" | docker login -u "$DOCKERHUB_USER" --password-stdin
                        docker build -t "$DOCKERHUB_REPO:$BUILD_NUMBER" .
                        docker push "$DOCKERHUB_REPO:$BUILD_NUMBER"
                        docker tag "$DOCKERHUB_REPO:$BUILD_NUMBER" "$DOCKERHUB_REPO:latest"
                        docker push "$DOCKERHUB_REPO:latest"
                        docker logout
                    '''
                }
            }
        }
    }
}