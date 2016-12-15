package com.example.lost_and_found;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import com.example.Datajavabean.UserTable;

public class LoginActivity extends Activity{
	private EditText et_phone,et_verification_code;
	private Button bt_getCode,bt_login;
	private String APPKEY="19ebceac28fb0";
    private String APPSECRET="e7951271b158b161a7daaf1f4aea8a9c";
    private EventHandler eventhand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.phonenumber_signing);
    	SMSSDK.initSDK(LoginActivity.this, APPKEY, APPSECRET);
    	
    	Bmob.initialize(LoginActivity.this, "6366d2f68dcf0c5f3d731247aa767264");
    	bindfindviewbyid();
    	
    	eventhand=new EventHandler(){
    		@Override
    		public void afterEvent(int event, int result, Object data) {
    			
    		   if (result == SMSSDK.RESULT_COMPLETE) {
    			 //回调完成
    			if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
    				//提交验证码成功,跳转页面	
    				final String phoneStr=et_phone.getText().toString();
    				BmobQuery<UserTable> query=new BmobQuery<UserTable>();
    				query.addWhereEqualTo("phonenum", phoneStr);
    				query.findObjects(new FindListener<UserTable>() {
						
						@Override
						public void done(List<UserTable> arg0, BmobException e) {
							// TODO Auto-generated method stub
							if (e==null) {
								Intent intent=new Intent(LoginActivity.this,firstpage.class);
			    				startActivity(intent);
							}
							else 
							{
								UserTable user=new UserTable();
								user.setPhonenum(phoneStr);
								user.save(new SaveListener<String>() {									
									@Override
									public void done(String arg0, BmobException a) {
										// TODO Auto-generated method stub
										if(a==null){
											Intent intent=new Intent(LoginActivity.this,firstpage.class);
						    				startActivity(intent);
										}								
									}
								});
							}
						}
					});
    				Intent intent=new Intent(LoginActivity.this,MainActivity.class);
    				startActivity(intent);   				         
    			}else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
    				//获取验证码成功   				
    			}
    				             
              }else{                                                                 
                 ((Throwable)data).printStackTrace(); 
          }
      } 
    }; 
    SMSSDK.registerEventHandler(eventhand);//注册短信回调
    //为“获取验证码”按钮设置点击监听
        bt_getCode.setOnClickListener(new android.view.View.OnClickListener() {
    					
    		@Override
    		public void onClick(View arg0) {
    			// TODO Auto-generated method stub
    			//判断手机号码是否为空
    			if(et_phone.getText().toString().equals("")==false){
                    //判断手机号码是否正确
    				if(et_phone.getText().length()==11)
    				{   	
    					SMSSDK.getVerificationCode("86", et_phone.getText().toString());
    				}
    				else {
    					Toast.makeText(LoginActivity.this, "请输入正确的手机号码！", Toast.LENGTH_LONG).show();
    				}
    			}else {
    				Toast.makeText(LoginActivity.this, "手机号码不能为空！", Toast.LENGTH_LONG).show();
    			}
    		}
    		 
    	});
        et_verification_code.addTextChangedListener(new TextWatcher() {
    		
    		@Override
    		public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
    			// TODO Auto-generated method stub
    			
    					if(et_verification_code.getText().toString().equals(""))
    					{
    						bt_login.setEnabled(false);
    					}
    					else {
    						bt_login.setEnabled(true);
    					}							
    		}
    		
    		@Override
    		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
    				int arg3) {
    			// TODO Auto-generated method stub
    			
    		}
    		
    		@Override
    		public void afterTextChanged(Editable arg0) {
    			// TODO Auto-generated method stub
    			
    		}
    	});
        bt_login.setOnClickListener(new OnClickListener() {
    		
    		@Override
    		public void onClick(View arg0) {
    			// TODO Auto-generated method stub
    			SMSSDK.submitVerificationCode("86", et_phone.getText().toString(), et_verification_code.getText().toString());
    		}
    	});
    }
	private void bindfindviewbyid() {
    	// TODO Auto-generated method stub
    	et_phone=(EditText) findViewById(R.id.phonenum);
    	et_verification_code=(EditText)findViewById(R.id.input_verification_code);
    	bt_getCode=(Button)findViewById(R.id.get_verification_code);
    	bt_login=(Button)findViewById(R.id.login);
    }   
}