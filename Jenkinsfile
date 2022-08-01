/**
 В докере развернуть Jenkins.
 Работа проходит в два этапа:

 1 этап. Сборка и пуш образов в репозиторий
 - Скачивание из репозитория кода по тегу
 - Проход тестов. Если тесты падают сборка отменяется.
 - Сборка проекта.
 - Пушнуть образы проекта на докерхаб.

 2 этап. Деплой
 - подключиться по ssh к виртуальной машине или контейнеру
 - pull образа из репозитория
 - запуск образа
 */


pipeline {
    agent any

    tools {
        gradle "7.4"
    }

    environment {
        DOCKERHUB_CREDENTIALS=credentials('docker-hub')
    }

    stages {
        stage('Build and push') {
            steps {
                echo '************** testing ****************'
                sh 'gradle clean test --no-daemon'

                echo '************** building ****************'
                sh 'gradle clean build'

                echo '************** create docker image and pushing to docker hub ****************'
                sh 'docker build -t raketa30/manager:latest .'
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                sh 'docker push raketa30/manager:latest'
                echo 'test'
            }
        }
    }

    post {
        always {
            sh 'docker logout'
        }
    }

}