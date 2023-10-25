package publishers;

import java.io.InputStream;
import java.util.Properties;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

import dataTypes.DtClass;
import dataTypes.DtUser;
import exceptions.AtributeAlreadyExists;
import exceptions.EmptyRequiredFieldException;
import exceptions.FebruaryDayException;
import exceptions.SameYearException;
import interfaces.ControllerFactory;
import interfaces.UserInterface;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class UserPublisher {
	private ControllerFactory factory;
	private UserInterface ucon;
	private Endpoint endpoint;
	private String baseUrl;
	
	public UserPublisher() {
		factory = ControllerFactory.getInstance();
		ucon = factory.getUserInterface();
		try {
			InputStream configStream = UserPublisher.class.getClassLoader().getResourceAsStream("config.properties");
			if (configStream != null) {
				Properties properties = new Properties();
				properties.load(configStream);
				baseUrl = properties.getProperty("baseUrl");
			} 
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}

	@WebMethod(exclude = true)
	public void publish() {
		System.out.println(baseUrl + "/userController");
		endpoint = Endpoint.publish(baseUrl + "/userController", this);
		System.out.println(baseUrl + "/userController");
	}
	
	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
        return endpoint;
	}
	
	//LOS MÉTODOS QUE VAMOS A PUBLICAR
	@WebMethod
	public void updateUserInfo(DtUser updatedUser) throws EmptyRequiredFieldException, FebruaryDayException, SameYearException {
		ucon.updateUserInfo(updatedUser);
	}
	
	@WebMethod
	public void newMember(DtUser DtNewUser) {
		ucon.newMember(DtNewUser);
	}
	
	@WebMethod
	public boolean existsNickname(String nickName) {
		return ucon.existsNickname(nickName);
	}
	
	@WebMethod
	public boolean existsEmail(String email){
		return ucon.existsEmail(email);
	}
	
	@WebMethod
	public void newUser(DtUser newUser, String institute) throws EmptyRequiredFieldException, FebruaryDayException, SameYearException, AtributeAlreadyExists{
		ucon.newUser(newUser, institute);
	}
	
	@WebMethod
	public DtClass [] getMemberEnrolledClasses(String nickname) {
		return (DtClass[]) ucon.getMemberEnrolledClasses(nickname).entrySet().toArray();
	}
	
	@WebMethod
	public void addEnrollment(String className, DtUser user, Float price) throws Exception {
		ucon.addEnrollment(className, user, price);
	}
	@WebMethod
	public String[] listMembersByNickname() {
		return ucon.listMembersByNickname();
	}
}
