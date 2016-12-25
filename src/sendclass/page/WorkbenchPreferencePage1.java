package sendclass.page;

import java.io.File;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import sendclass.Activator;
import sendclass.utils.CryptoUtil;

public class WorkbenchPreferencePage1 extends PreferencePage implements IWorkbenchPreferencePage {

	//定义属性页键值key
	private static final String KEY_CLASS_PRE_PATH="KEY_CLASS_PRE_PATH";
	private static final String KEY_WEB_SRC_PATH="KEY_WEB_SRC_PATH";
	private static final String KEY_PSCP_PATH="KEY_PSCP_PATH";
	private static final String KEY_USERNAME="KEY_USERNAME";
	private static final String KEY_PASSWORD="KEY_PASSWORD";
	private static final String KEY_PORT="KEY_PORT";
	private static final String KEY_HOST="KEY_HOST";
	private static final String KEY_PRI_KEY_PATH="KEY_PRI_KEY_PATH";
	// 定义属性默认值
	public static final boolean DEF_IS_OPEN_FILE =true;
	public static final String DEF_CLASS_PRE_PATH ="WEB-INF"+File.separator+"classes"+File.separator;
	public static final String DEF_WEB_SRC_PATH ="web"+File.separator;
	public static final String DEF_PSCP_PATH ="D:"+File.separator+"PSCP.EXE";
	public static final String DEF_PORT ="22";
	public static final String DEF_HOST ="localhost";
	
	// 属性页 需要设置的信息
	private Text textClassPrePath;
	private Text textWebSrcPath;
	private Text textPscpPath;
	private Text textUserName;
	private Text textPassword;
	private Text textPort;
	private Text textHost;
	private Text textPriKeyPath;
	private Button buttonPSCP;
	private Button buttonPriKey;
	// 加密盐值
	private static final String CRYPTO_KEY="2c70e12b7a0646f9";
	
	private static final int X=5;
	private static final int Y=5;
	private static final int HEIGHT=21;
	private static final int TEXT_LEN=300;
	private static final int TEXT_WIGHT=17;
	// 定义一个IPreferenceStore对象
   private IPreferenceStore ps;
	 
	public WorkbenchPreferencePage1() {
		super();
	}

	public WorkbenchPreferencePage1(String title) {
		super(title);
	}

	public WorkbenchPreferencePage1(String title, ImageDescriptor image) {
		super(title, image);
	}

	protected Control createContents(Composite parent) {
		int rows=0;
		ps = getPreferenceStore();// 取得一个IPreferenceStore对象
		Composite composite1 = new Composite(parent, SWT.LEFT);
		int nameLen = 85;
		Label label = new Label(composite1, SWT.RIGHT);
		label.setText("class目录前缀:");
		label.setBounds(X,y(rows),nameLen,TEXT_WIGHT);
		textClassPrePath = new Text(composite1, SWT.LEFT);
		textClassPrePath.setText(classPrePath(ps));
		textClassPrePath.setBounds(X*2+nameLen, y(rows++), TEXT_LEN-nameLen, TEXT_WIGHT);
		label = new Label(composite1, SWT.RIGHT);
		label.setText("web目录设置:");
		label.setBounds(X,y(rows),nameLen,TEXT_WIGHT);
		textWebSrcPath = new Text(composite1, SWT.LEFT);
		textWebSrcPath.setText(webSrcPath(ps));
		textWebSrcPath.setBounds(X*2+nameLen, y(rows++), TEXT_LEN-nameLen, TEXT_WIGHT);
		
		Composite composite2 = new Composite(parent, SWT.LEFT);
		rows=0;
		label = new Label(composite2, SWT.LEFT);
		label.setText("PSCP.EXE程序:");
		label.setBounds(X,y(rows++),300,TEXT_WIGHT);
		textPscpPath = new Text(composite2, SWT.LEFT);
		textPscpPath.setText(textPacpPath(ps));
		textPscpPath.setBounds(X, y(rows), 300, TEXT_WIGHT);
		buttonPSCP = new Button(composite2, SWT.NONE); 
		buttonPSCP.setBounds(X+305, y(rows++), 70, TEXT_WIGHT);
		buttonPSCP.setText("选择文件..."); 
		buttonPSCP.setToolTipText("单击此按钮,选择PSCP.EXE文件路径!");	
		buttonPSCP.setLayoutData(new GridData(70, TEXT_WIGHT)); 
		buttonPSCP.addMouseListener(new MouseAdapter() {
			public void mouseUp(MouseEvent e) {
				FileDialog fd = new FileDialog(getShell(), SWT.OPEN | SWT.SINGLE);
				String result = fd.open();
				textPscpPath.setText(result);
			}
		});
		
		Composite composite3 = new Composite(parent, SWT.LEFT);
		rows=0;
		nameLen = 85;
		label = new Label(composite3, SWT.RIGHT);
		label.setText("用户名:");
		label.setBounds(X,y(rows),nameLen,TEXT_WIGHT);
		textUserName = new Text(composite3, SWT.LEFT);
		textUserName.setText(textUserName(ps));
		textUserName.setBounds(X*2+nameLen, y(rows++), TEXT_LEN-nameLen, TEXT_WIGHT);
		label = new Label(composite3, SWT.RIGHT);
		label.setText("密   码:");
		label.setBounds(X,y(rows),nameLen,TEXT_WIGHT);
		textPassword = new Text(composite3, SWT.LEFT|SWT.PASSWORD);
		textPassword.setText(textPassword(ps));
		textPassword.setBounds(X*2+nameLen, y(rows++), TEXT_LEN-nameLen, TEXT_WIGHT);
		label = new Label(composite3, SWT.RIGHT);
		label.setText("上传服务器:");
		label.setBounds(X,y(rows),nameLen,TEXT_WIGHT);
		textHost = new Text(composite3, SWT.LEFT);
		textHost.setText(textHost(ps));
		textHost.setBounds(X*2+nameLen, y(rows++), TEXT_LEN-nameLen, TEXT_WIGHT);
		label = new Label(composite3, SWT.RIGHT);
		label.setText("ssh端口:");
		label.setBounds(X,y(rows),nameLen,TEXT_WIGHT);
		textPort = new Text(composite3, SWT.LEFT);
		textPort.setText(textPort(ps));
		textPort.setBounds(X*2+nameLen, y(rows++), TEXT_LEN-nameLen, TEXT_WIGHT);
		
		Composite composite4 = new Composite(parent, SWT.LEFT);
		rows=0;
		label = new Label(composite4, SWT.LEFT);
		label.setText("Putty格式私钥文件路径(OPENSSH的私钥请使用PUTTYGEN.EXE转换):");
		label.setBounds(X,y(rows++),400,TEXT_WIGHT);
		textPriKeyPath = new Text(composite4, SWT.LEFT);
		textPriKeyPath.setText(textPriKeyPath(ps));
		textPriKeyPath.setBounds(X, y(rows), TEXT_LEN, TEXT_WIGHT);
		buttonPriKey = new Button(composite4, SWT.NONE); 
		buttonPriKey.setBounds(X*2+TEXT_LEN, y(rows++), 70, TEXT_WIGHT);
		buttonPriKey.setText("选择文件..."); 
		buttonPriKey.setToolTipText("单击此按钮,选择Putty私钥文件路径!");	
		buttonPriKey.setLayoutData(new GridData(70, TEXT_WIGHT)); 
		buttonPriKey.addMouseListener(new MouseAdapter() {
			public void mouseUp(MouseEvent e) {
				FileDialog fd = new FileDialog(getShell(), SWT.OPEN | SWT.SINGLE);
				String result = fd.open();
				textPriKeyPath.setText(result);
			}
		});
		
		return parent;
	}
	private int y(int count){
		return Y+HEIGHT*count;
	}
	protected IPreferenceStore doGetPreferenceStore() {
		return super.doGetPreferenceStore();
	}

	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	/**
	 * 重置属性值
	 *  (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	protected void performDefaults() {
		buttonPSCP.setSelection(DEF_IS_OPEN_FILE);
		textClassPrePath.setText(DEF_CLASS_PRE_PATH);
		textWebSrcPath.setText(DEF_WEB_SRC_PATH);
		textPscpPath.setText(DEF_PSCP_PATH);
		textUserName.setText("");
		textPassword.setText("");
		textPort.setText(DEF_PORT);
		super.performDefaults();
	}

	/**
	 * Apply 保存的时候 设置相关连的功能属性静态变量即可!
	 *  (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performOk()
	 */
	public boolean performOk() {
		ps.setValue(KEY_CLASS_PRE_PATH, textClassPrePath.getText());
		ps.setValue(KEY_WEB_SRC_PATH, textWebSrcPath.getText());
		ps.setValue(KEY_PSCP_PATH, textPscpPath.getText());
		ps.setValue(KEY_USERNAME, textUserName.getText());
		ps.setValue(KEY_PASSWORD, CryptoUtil.encryptAES(textPassword.getText(),CRYPTO_KEY ));
		ps.setValue(KEY_PORT, textPort.getText());
		ps.setValue(KEY_HOST, textHost.getText());
		return super.performOk();
	}
	
	public static String classPrePath(IPreferenceStore ps){
		String targetPath_  =ps.getString(KEY_CLASS_PRE_PATH);
		String targetPath = (targetPath_==null||targetPath_.length()==0)?DEF_CLASS_PRE_PATH:targetPath_;
		return targetPath;
	} 
	public static String webSrcPath(IPreferenceStore ps){
		String Path_  =ps.getString(KEY_WEB_SRC_PATH);
		String path = (Path_==null||Path_.length()==0)?DEF_WEB_SRC_PATH:Path_;
		return path;
	} 
	public static String textPacpPath(IPreferenceStore ps){
		String Path_  =ps.getString(KEY_PSCP_PATH);
		String path = (Path_==null||Path_.length()==0)?DEF_PSCP_PATH:Path_;
		return path;
	} 
	public static String textPriKeyPath(IPreferenceStore ps){
		String Path_  =ps.getString(KEY_PRI_KEY_PATH);
		String path = (Path_==null||Path_.length()==0)?"":Path_;
		return path;
	} 
	public static String textUserName(IPreferenceStore ps){
		String Path_  =ps.getString(KEY_USERNAME);
		String path = (Path_==null||Path_.length()==0)?"":Path_;
		return path;
	} 
	public static String textPassword(IPreferenceStore ps){
		String Path_  =ps.getString(KEY_PASSWORD);
		String path = (Path_==null||Path_.length()==0)?"":Path_;
		return path;
	} 
	public static String textPort(IPreferenceStore ps){
		String Path_  =ps.getString(KEY_PORT);
		String path = (Path_==null||Path_.length()==0)?DEF_PORT:Path_;
		return path;
	} 
	public static String textHost(IPreferenceStore ps){
		String Path_  =ps.getString(KEY_HOST);
		String path = (Path_==null||Path_.length()==0)?DEF_HOST:Path_;
		return path;
	} 
}
