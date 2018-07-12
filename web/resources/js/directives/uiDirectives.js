angular.module("form").directive("uiNumber", function () {
	return {
		require: "ngModel",
		link: function (scope, element, attrs, ctrl) {
			var _formatDate = function (date) {
				date = date.replace(/[^0-9^]+/g, "");
				
				return date;
			};
			
			element.bind("keyup", function () {
				ctrl.$setViewValue(_formatDate(ctrl.$viewValue));
				ctrl.$render();
			});
		}
	};
});

