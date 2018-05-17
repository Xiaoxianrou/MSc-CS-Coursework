/**
 * validate-QUIZ.js
 * 
 * 1. Using Object-oriented Programming by JaveScript object prototype attribute.
 * 2. Using Defensive Programming by JavaScript closure ';(function(){})()' to wrap the whole code, 
 *    use 'window.validate = validate' to make the validate function global. 
 *    so other object cannot access in glabal. it makes the running environment clean and safe
 * 
 * Related Knowledge Sources:
 * 1. Closure (https://developer.mozilla.org/en-US/docs/Web/JavaScript/Closures)
 * 2. Inheritance and the prototype chain (https://developer.mozilla.org/en-US/docs/Web/JavaScript/Inheritance_and_the_prototype_chain)
 * 
 * @author Junyan Wang
 * @version 1.0
 */

;(function () {
    /**
     * Validation of British language quiz form 
     * 
     * @class Validation
     */
    var Validation = function (formDom) {
        /**
         * Field
         * 
         * {String} form   quiz form DOM
         * {String} name   name
         * {String} firstVal   fisrt value
         * {String} secondVal   second value
         * {String} thirdVal   third value
         * {String} fourthVal   fourth value
         * {String} firstAnswer   fisrt right answer
         * {String} secondAnswer   second right answer
         * {String} thirdAnswer   third right answer
         * {String} fourthAnswer   fourth right answer
         */
        this.form = formDom;
        this.name = '';
        // user answers
        this.firstVal = '';
        this.secondVal = [];
        this.thirdVal = '';
        this.fourthVal = '';
        // right answers
        this.firstAnswer = 'c';
        this.secondAnswer = ['a', 'c'];
        this.thirdAnswer = 'b';
        this.fourthAnswer = 'british, australian and new zealand sign language';
    }

    /**
     * Methods of Class Validation
     * 
     * {Function} check
     * {Function} getValue
     * {Function} checkEmpty
     */
    Validation.prototype = {
        /**
         * init
         * 
         * @return {Boolean} valid or not
         */
        init: function () {
            this.name = this.form.UserInfo.value;
            this.firstVal = this.form.Q1.value;
            this.secondVal = this.getSecondValue();
            this.thirdVal = this.getThirdValue();
            this.fourthVal = this.form.Q4.value;

            if (!this.check()) return false;

            this.score();

            return true;
        },

        /**
         * getSecondValue
         * get questions two value
         * 
         * @return {Array} question 2 answer array
         */
        getSecondValue: function () {
            var arr = [];
            if (this.form.Q2a.checked) arr.push('a');
            if (this.form.Q2b.checked) arr.push('b');
            if (this.form.Q2c.checked) arr.push('c');
            if (this.form.Q2d.checked) arr.push('d');

            return arr;
        },

        /**
         * getThirdValue
         * get questions three value
         * 
         * @return {String} question 3 answer
         */
        getThirdValue: function () {
            var res = '',
                optionA = document.getElementById('Q3a'),
                optionB = document.getElementById('Q3b'),
                optionC = document.getElementById('Q3c'),
                optionD = document.getElementById('Q3d');

            if (optionA.checked) res = 'a';
            if (optionB.checked) res = 'b';
            if (optionC.checked) res = 'c';
            if (optionD.checked) res = 'd';

            return res;
        },

        /**
         * check
         * check the name and ansers
         * 
         * @return {Boolean} valid or not
         */
        check: function () {
            if (!this.checkName()) return false;
            if (!this.checkEmpty()) return false;
            if (!this.checkSecondVals()) return false;

            return true;
        },

        /**
         * score
         * Get the user's score
         */
        score:function () {
            var score = 0,
                scoreDom = this.form.thisScore;

            if (this.firstVal === this.firstAnswer) score += 1;
            if (this._indexOf(this.secondAnswer[0], this.secondVal) > -1) score += 1;
            if (this._indexOf(this.secondAnswer[1], this.secondVal) > -1) score += 1;
            if (this.thirdVal === this.thirdAnswer) score += 1;
            if (this.fourthVal.toLowerCase() === this.fourthAnswer) score += 1;

            scoreDom.value = score;
            alert('Your score is ' + score);
        },

        /**
         * checkName
         * check name empty or nat
         * 
         * @return {Boolean} empty or not
         */
        checkName: function () {
            var res = true,
                msg = '';
            if (!this.name) {
                res = false;
                msg = 'You should enter your name first.';
                alert(msg);
            }

            return res;
        },

        /**
         * checkEmpty
         * check every questions empty or not
         * 
         * @return {Boolean} empty or not
         */
        checkEmpty: function () {
            var res = true,
                msg = '',
                domQ1 = document.getElementById('Q1'),
                domQ2 = document.getElementById('Q2'),
                domQ3 = document.getElementById('Q3'),
                domQ4 = document.getElementById('Q4');

            if (!this.firstVal) {
                msg += 'Question one has not been answered; \n';
                domQ1.style.background = 'yellow';
                res = false;
            }else {
                domQ1.style.background = '';
            }

            if (!this.secondVal.length) {
                msg += 'Question two has not been answered; \n';
                domQ2.style.background = 'yellow';
                res = false;
            }else {
                domQ2.style.background = '';
            }

            if (!this.thirdVal) {
                msg += 'Question three has not been answered; \n';
                domQ3.style.background = 'yellow';
                res = false;
            }else {
                domQ3.style.background = '';
            }

            if (!this.fourthVal) {
                msg += 'Question four has not been answered; \n';
                domQ4.style.background = 'yellow';
                res = false;
            }else {
                domQ4.style.background = '';
            }

            if (msg) alert(msg);

            return res;
        },

        /**
         * checkSecondVals
         * ensure the user only selected 2 options for question 2
         * 
         * @return {Boolean} empty or not
         */
        checkSecondVals:function () {
            var res = true,
                msg = '',
                domQ2 = document.getElementById('Q2');

            if (this.secondVal.length != 2) {
                msg = 'You can only select 2 options for question 2.';
                domQ2.style.background = 'yellow';
                alert(msg);
                res = false;
            }else {
                domQ2.style.background = '';
            }

            return res;
        },

        /**
         * _indexOf
         * return the index of given item
         * IE8 js version doesn't have indexOf function of array
         * 
         * @param {String} item  target item
         * @param {Array} arr  target array
         * @return {Number} index of items
         */
        _indexOf: function (item,arr) {
            var index = -1;

            for(var i = 0; i < arr.length; i++){
                if (item === arr[i]) {
                    index = i;
                }
            }

            return index;
        },
    }

    // Set constructor of Class Validation
    Validation.prototype.constructor = Validation;

    // get an instance of Class Validation, then return the result of its init method
    var validate = function () {
        var dom = document.forms[0];
        var result = new Validation(dom);
        return result.init();
    }

    // make the validate global, can be invoked by submit buttion
    window.validate = validate;
})()