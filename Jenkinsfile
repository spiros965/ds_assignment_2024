pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Docker Build') {
            steps {
                sh 'docker build -t michail26/ds-app:latest .'
            }
        }
        stage('Push') {
            steps {
                sh 'docker push michail26/ds-app:latest'
            }
        }
    }
}