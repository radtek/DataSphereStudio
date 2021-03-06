/*
 * Copyright 2019 WeBank
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.webank.wedatasphere.dss.appjoint.schedulis.jobtype.execution

import java.util

import com.webank.wedatasphere.dss.appjoint.AppJoint
import com.webank.wedatasphere.dss.appjoint.execution.NodeExecution
import com.webank.wedatasphere.dss.appjoint.service.AppJointUrlImpl

/**
  * Created by enjoyyin on 2019/11/5.
  */
class EventCheckerAppJoint extends AppJointUrlImpl with AppJoint {

  private var params: util.Map[String, AnyRef] = _
  private var nodeExecution: NodeExecution = _

  override def getAppJointName: String = "EventChecker"

  override def init(baseUrl: String, params: util.Map[String, AnyRef]): Unit = {
    setBaseUrl(baseUrl)
    this.params = params
  }

  override def getNodeExecution: NodeExecution = {
    if(nodeExecution == null) synchronized {
      if(nodeExecution == null) {
        nodeExecution = new EventCheckerNodeExecution()
        nodeExecution.setBaseUrl(getBaseUrl)
        nodeExecution.init(params)
      }
    }
    nodeExecution
  }
}
