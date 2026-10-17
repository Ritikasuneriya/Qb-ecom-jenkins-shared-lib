
def call(Map config = [:]) {
    def imageName = config.imageName
    def imageTag = config.imageTag
    def dockerfile = config.dockerfile ?: 'Dockerfile'
    def context = config.context ?: '.'

    if (!imageName || !imageTag) {
        error 'imageName and imageTag are required.'
    }

    echo "Building Docker image: ${imageName}:${imageTag}"

    sh """
        set -eu
        docker build \
          -f '${dockerfile}' \
          -t '${imageName}:${imageTag}' \
          '${context}'
    """

    echo "Docker image built successfully: ${imageName}:${imageTag}"
}
