require('./config');

const chalk = require('chalk');

const app = require('./app');
app.listen(app.get('port'), _ => {
    console.log('%s App is running at http://%s:%d in %s mode', chalk.green('RUNNING:'), app.get('host'), app.get('port'), app.get('env'));
    require('./util/document')(app);
});
