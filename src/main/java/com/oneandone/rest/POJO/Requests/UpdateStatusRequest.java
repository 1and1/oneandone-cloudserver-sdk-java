/*
 * Copyright 2016 aliba.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.oneandone.rest.POJO.Requests;

import com.oneandone.rest.POJO.Response.Types.ServerAction;
import com.oneandone.rest.POJO.Response.Types.ServerActionMethod;

/**
 *
 * @author aliba
 */
public class UpdateStatusRequest  extends BaseRequest{

    private ServerAction action;
    private ServerActionMethod method;

    /**
     * @return the action
     */
    public ServerAction getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(ServerAction action) {
        this.action = action;
    }

    /**
     * @return the method
     */
    public ServerActionMethod getMethod() {
        return method;
    }

    /**
     * @param method the method to set
     */
    public void setMethod(ServerActionMethod method) {
        this.method = method;
    }

}
