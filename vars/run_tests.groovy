
def call() {
    echo 'Starting unit tests...'

    sh '''
        set -eu

        node --version
        npm --version

        if [ -f package-lock.json ]; then
            npm ci
        else
            npm install
        fi

        if node -e "
          const p = require('./package.json');
          process.exit(p.scripts && p.scripts.test ? 0 : 1);
        "; then
            npm test
        else
            echo 'WARNING: No test script found in package.json.'
            echo 'Unit tests skipped; configure real tests before production deployment.'
        fi
    '''

    echo 'Test stage completed.'
}
