pipeline 
{
    agent any
   
    stages
    {
        stage ('Initialize the Maven')
        {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                ''' 
            }
        }
        stage ('Build the maven project with testing parameter') 
        {
            steps 
            {
                //env.environment
                //env.module
                //env.exeType
                //env.
                sh 'mvn clean test -Dmaven.test.failure.ignore=true'
                
                script {
	                    try {
	                        // do something that fails
	                        //sh "exit 1"
	                        currentBuild.result = 'SUCCESS'
	                    } catch (Exception err) {
	                        currentBuild.result = 'SUCCESS'
	                    }
	                } 
            }
            
            post 
            {
               
                    archiveArtifacts artifacts:'Report/**/**/*.html', fingerprint: true
                    archiveArtifacts artifacts:'target/surefire-reports/index.html', fingerprint: true
                    echo 'Successfully!'
                     success 
                  {
	               
                }
                
		        failure {
		       		currentBuild.result = 'SUCCESS'
		            echo 'Failed!'
		        }
		        unstable {
		            echo 'This will run only if the run was marked as unstable'
		        }
		        changed {
		            echo 'This will run only if the state of the Pipeline has changed'
		            echo 'For example, if the Pipeline was previously failing but is now successful'
		        }
                
            }
            
            
            
        }
        
    }
}
