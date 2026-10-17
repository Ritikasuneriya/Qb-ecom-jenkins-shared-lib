
def call() {
    def appImage = "${env.DOCKER_IMAGE_NAME}:${env.DOCKER_IMAGE_TAG}"
    def migrationImage = "${env.DOCKER_MIGRATION_IMAGE_NAME}:${env.DOCKER_IMAGE_TAG}"

    echo 'Starting Trivy vulnerability scan...'

    sh """
        set -eu

        command -v trivy >/dev/null 2>&1 || {
            echo 'ERROR: Trivy is not installed on the Jenkins agent.'
            exit 1
        }

        trivy image --exit-code 1 \
          --severity HIGH,CRITICAL \
          '${appImage}'

        trivy image --exit-code 1 \
          --severity HIGH,CRITICAL \
          '${migrationImage}'
    """

    echo 'Trivy scan completed successfully.'
}
