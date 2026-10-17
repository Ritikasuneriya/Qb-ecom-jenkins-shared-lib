
def call() {
    echo 'Starting unit tests...'

    sh '''
        set -eu

        if [ -f package.json ]; then
            if [ -f package-lock.json ]; then
                npm ci
            fi

            npm test

        elif [ -f pytest.ini ] || [ -f pyproject.toml ] || [ -d tests ]; then
            python3 -m pip install -r requirements.txt 2>/dev/null || true
            python3 -m pytest

        else
            echo "ERROR: No supported test configuration found."
            echo "Configure the appropriate test command in run_tests.groovy."
            exit 1
        fi
    '''

    echo 'Unit tests completed successfully.'
}
