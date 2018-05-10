/*******************************************************************************
 * Copyright 2016 Adobe Systems Incorporated
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

"use strict";

/**
 * Title foundation component JS backing script
 */
use(function () {

    // TODO: change currentStyle to wcm.currentStyle

    var CONST = {
        PROP_TITLE: "./title",
        PROP_TEXT: "./text",
        PROP_TYPE: "type",
        PROP_DEFAULT_TYPE: "defaultType"
    }

    var titletext = {};

    // The actual titletext content
    //titletext.text = granite.resource.properties[CONST.PROP_TEXT]
    titletext.text = properties.get(CONST.PROP_TEXT)
            || granite.resource.properties["fieldDescription"];


    titletext.title = properties.get(CONST.PROP_TITLE)
            || granite.resource.properties["fieldDescription"];

    // The HTML element name
    titletext.element = granite.resource.properties[CONST.PROP_TYPE]
            || currentStyle.get(CONST.PROP_DEFAULT_TYPE, "");

    // Adding the constants to the exposed API
    titletext.CONST = CONST;
    
    // Initialise hasValue function
    var hasValue = false;
    if ((titletext.title != null) && (titletext.title.length()!=0)) {
            hasValue = true;
    } 
    titletext.hasValue = hasValue;

    return titletext;

});
