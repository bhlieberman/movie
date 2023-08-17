// This file was automatically generated from simple.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace examples.simple.
 */

if (typeof examples == 'undefined') { var examples = {}; }
if (typeof examples.simple == 'undefined') { examples.simple = {}; }


examples.simple.helloWorld = function(opt_data, opt_ignored) {
  return 'Hello world!';
};
if (goog.DEBUG) {
  examples.simple.helloWorld.soyTemplateName = 'examples.simple.helloWorld';
}


examples.simple.helloName = function(opt_data, opt_ignored) {
  return '' + ((! opt_data.greetingWord) ? 'Hello ' + soy.$$escapeHtml(opt_data.name) : soy.$$escapeHtml(opt_data.greetingWord) + ' ' + soy.$$escapeHtml(opt_data.name) + '!');
};
if (goog.DEBUG) {
  examples.simple.helloName.soyTemplateName = 'examples.simple.helloName';
}
