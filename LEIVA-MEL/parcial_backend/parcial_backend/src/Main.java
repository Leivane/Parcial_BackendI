import com.dh.dao.OdontologoH2;
import com.dh.modelo.Odontologo;
import com.dh.service.OdontologoService;
import com.dh.util.H2Gestor;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        Connection connection = null;
        try {
           // LogGestor.inicializar();
            connection= H2Gestor.getConnection();
            if(!connection.isClosed()){
                logger.info("La conexión está abierta");
            }
            OdontologoH2 odontologoH2 = new OdontologoH2();
            odontologoH2.crearTabla(connection);

            OdontologoService odontologoService = new OdontologoService(odontologoH2);



            odontologoService.agregarOdontologo(connection, 2364,"Ludwin", "Misses");
            odontologoService.agregarOdontologo(connection, 2365,"Juan", "Alberdi");
            odontologoService.agregarOdontologo(connection, 2366,"Milton", "Friedman");
            odontologoService.agregarOdontologo(connection, 2367,"Friederich", "Hayek");
            odontologoService.listarOdontologos(connection);
            connection.isClosed();
            logger.info("la conexion esta cerrada");

            /*Odontologo odontologo = new Odontologo();
            odontologo.setNombre("Marcos");
            odontologo.setApellido("Leiva");
            odontologo.setNumeroMatricula(2365);

            odontologoH2.update(odontologo);*/


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
