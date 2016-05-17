var gulp = require('gulp');
var connect = require('gulp-connect');

gulp.task('connect', function() {
  connect.server({
    root: 'app',
    port: 4000,
    livereload: true
  });
});

gulp.task('default', ['connect']);
