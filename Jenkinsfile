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
                        sh 'gradle clean test --no-daemon' //run a gradle task
                    } finally {
                        junit '**/build/test-results/test/*.xml' //make the junit test results available in any case (success & failure)
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