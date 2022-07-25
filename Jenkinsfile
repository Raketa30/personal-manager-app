pipeline {

    agent any

    tools {
        gradle "7.4"
    }

    stages {
        stage('test') {
            steps {
                script {
                    try {
                        sh 'gradle clean test --no-daemon'
                    } finally {
                    }
                }
            }

        }

        stage('build') {
            steps {
                script {
                    sh 'gradle build'
                }
            }
        }

    }
}