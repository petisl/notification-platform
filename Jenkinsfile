pipeline {
    agent any

    stages {

        stage('Build Docker & Rename Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'gitlab', passwordVariable: 'password', usernameVariable: 'username')]) {
                    sh '''
                      docker login registry.gitlab.com --username $username --password $password
                      docker build --rm -t registry.gitlab.com/hrdrogenhr/notification-service:"$BUILD_NUMBER" .
                    '''
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'gitlab', passwordVariable: 'password', usernameVariable: 'username')]) {
                    sh '''
                      docker login registry.gitlab.com --username $username --password $password
                      docker push registry.gitlab.com/hrdrogenhr/notification-service:"$BUILD_NUMBER"
                    '''
                }
            }
        }

        stage('Docker Running Services') {

           steps {
               withCredentials([usernamePassword(credentialsId: 'gitlab', passwordVariable: 'password', usernameVariable: 'username')]) {
                 sh '''
                     docker login registry.gitlab.com --username $username --password $password
                     docker service update --force --image registry.gitlab.com/hrdrogenhr/notification-service:"$BUILD_NUMBER" notification-service
                     #docker service create --publish 8087:8087 --mode global --constraint node.hostname==centos-s-2vcpu-4gb-intel-nyc1-01 --network hydrogenhr-overlay --update-delay 10s --restart-condition on-failure --env-file /opt/app/env/hydrogenhr.txt --with-registry-auth --name notification-service registry.gitlab.com/hrdrogenhr/notification-service:"$BUILD_NUMBER"
                    '''
               }
           }
        }

        stage('Remove Dangling Docker Images & Containers') {
           steps {
              sh '''
                  #docker image prune -f
                  #docker container prune -f
               '''
            }
        }

       stage('Finish Line') {
           steps {
              echo 'Congratulations! Your application has been deployed successfully.'
           }
       }

    }
}
