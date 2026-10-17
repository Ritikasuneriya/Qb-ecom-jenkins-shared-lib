def call(String repoUrl, String branchName) {
    echo "Cloning repository: ${repoUrl}, branch: ${branchName}"

    git(
        url: repoUrl,
        branch: branchName,
        credentialsId: 'github-credentials'
    )

    echo "Repository cloned successfully."
}
