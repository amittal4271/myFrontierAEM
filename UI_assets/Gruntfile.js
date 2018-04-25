module.exports = function(grunt) {

  grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),
    uglify: {
      options: {
        mangle: false
      },
      my_target: {
        files: [
          {src: ['js/bootstrap.min.js', 'js/swiper.min.js', 'js/jquery.hoverIntent.min.js', 'js/main.js', 'js/validate.js'], dest: 'prod-assets/frontier-minified.js'}
        ]
      }
    },
    cssmin: {
      add_banner: {
        options: {
          banner: '/* My minified css file */'
        },
        files: [
          {src: ['css/bootstrap.min.css', 'css/frontier-less.css'], dest: 'prod-assets/frontier-less-minified.css'}
        ]
      }
    },
    watch: {
      options: {
        dateFormat: function(time) {
          grunt.log.writeln('The watch finished in ' + time + 'ms at' + (new Date()).toString());
          grunt.log.writeln('Waiting for more changes...');
        },
      },
      files: ['js/*.js', 'css/*.css', 'less/*.less'],
      tasks: ['less', 'uglify', 'cssmin']
    },
    less: {
      development: {
        options: {
          paths: ['css']
        },
        files: {
          'css/frontier-less.css': 'less/styles.less'
        }
      }
    }
  });

  grunt.loadNpmTasks('grunt-contrib-uglify');
  //grunt.loadNpmTasks('grunt-contrib-concat');
  grunt.loadNpmTasks('grunt-contrib-cssmin');
  //grunt.registerTask('test', ['jshint', 'qunit']);
  grunt.loadNpmTasks('grunt-contrib-less');
  grunt.loadNpmTasks('grunt-contrib-watch');
  grunt.registerTask('default', ['less', 'uglify', 'cssmin']);

};