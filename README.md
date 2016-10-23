# attendance-system
a simple attendance system


接口定义

1.登录接口 

URL：/user,method=POST

参数：
{
	"userName":"lijianming",
	"password":"1234"
}

返回结果：
	成功：
	{
		"status":"success"
	}
	失败：
	{
		"status":"fail",
		"errorMsg":"用户密码不正确"
	}


2.请假接口

URL: /apply,method=POST

参数：
{
	"type":"年假",
	"reason":"出去旅游",
	"handOver":"老大",
	"start":"2016-8-11",
	"end":"2016-8-12"
}

返回结果：
	成功：
	{
		"status":"success"
	}
	失败：
	{
		"status":"fail",
		"errorMsg":"用户状态不合法"
	}



3.审批接口

URL: /audit,method=PUT

参数：
{
	"auditStatus":"agree"或者"disagree"
	"remark":"请假太频繁"
} 

返回结果：
	成功：
	{
		"status":"success"
	}
	失败:
	{
		"status":"fail",
		"errorMsg":"用户状态不合法"
	}


4.获取当前用户的基本信息

URL: /user,method=GET

参数：
{}

返回结果:
	成功：
	{
		"status":"success",
		"data":{
			"userName":"lijianming"
		}
	}
	失败：
	{
		"status":"fail",
		"errorMsg":"用户状态不合法"
	}


5.获取当前用户的请假申请列表

URL: /apply,method=GET

参数：
{}

返回结果：
	成功：
	{
		"status":"success",
		"data":[
			{
				"applyId":"777",
				"type":"年假",
				"reason":"累了",
				"handOver":"老大",
				"startDate":"2016-8-11",
				"endDate":"2016-8-12",
				"status": "agreee"|"disagree"|"undetermined"
			},
			{
				...
			}
		]
	}
	失败：
	{
		"status":"fail",
		"errorMsg":"用户状态不合法"
	}

6.获取自己需要审批的申请列表

URL: /audit,method=GET

参数:
{}

返回结果:
	成功：
	{
		"status":"success",
		"data":[
			{
				"applyId":"777"
				"userId":"116",
				"userName":"tjullin",
				"type","年假"
				"reason","请假理由",
				"handOver":"老大",
				"startDate":"2016-8-11",
				"endDate":"2016-8-12",
				"status","agree"|"disagree"|"undetermined"
			},
			{
				...
			}
		]
	}
	失败：
	{
		"status":"fail",
		"errorMsg":"用户状态不合法"
	}


7.修改申请

URL: /apply,method=PUT

参数：(只填写要修改的参数)
{
	"applyId":"777",
	"type":"年假",
	"reason":"出去旅游",
	"handOver":"老大",
	"start":"2016-8-11",
	"end":"2016-8-12"	
}

返回结果：
	成功：
	{
		"status":"success"
	}
	失败：
	{
		"status":"fail",
		"errorMsg":"用户状态不合法"
	}


