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
                        [threshold: 70.0, metric: 'INSTRUCTION', baseline: 'PROJECT', criticality: 'FAILURE'],
                        [threshold: 70.0, metric: 'BRANCH', baseline: 'PROJECT', criticality: 'FAILURE']
                    ]
                )
            }
        }
    }
}