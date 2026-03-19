pipeline {
    agent any
    tools {
        maven 'Maven' // Adjust the Maven version as needed}
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
                jacoco(
                    execPattern: '**/target/*.exec',
                    classPattern: '**/target/classes',
                    sourcePattern: '**/src/main/java',
                    exclusionPattern: '**/src/test/**',

                    // Threshholds for coverage fails
                    instructionThreshold: '70',
                    branchThreshold: '70'
                )
            }
        }
    }
}