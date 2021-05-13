package guaong.quick.general;

import guaong.quick.core.create.creator.JavaCreator;
import guaong.quick.core.resovle.bean.TableDefinition;
import guaong.quick.general.create.*;
import guaong.quick.general.write.GeneralWriter;

import java.util.HashMap;
import java.util.Map;

public class AutoGeneration {

    private TableDefinition tableDefinition;

    public AutoGeneration(TableDefinition tableDefinition){
        this.tableDefinition = tableDefinition;
    }


    public boolean todo(){
        Map<Integer, String> generalMap = new HashMap<>();

        JavaCreator entity = new EntityCreator(tableDefinition);
        String entityStr = entity.create().toString();
        generalMap.put(GeneralWriter.ENTITY, entityStr);

        JavaCreator controller = new ControllerCreator(tableDefinition);
        String controllerStr = controller.create().toString();
        generalMap.put(GeneralWriter.CONTROLLER, controllerStr);

        JavaCreator serviceImpl = new ServiceImplCreator(tableDefinition);
        String serviceImplStr = serviceImpl.create().toString();
        generalMap.put(GeneralWriter.SERVICE_IMPL, serviceImplStr);

        JavaCreator iService = new IServiceCreator(tableDefinition);
        String iServiceStr = iService.create().toString();
        generalMap.put(GeneralWriter.I_SERVICE, iServiceStr);

        JavaCreator mapper = new MapperCreator(tableDefinition);
        String mapperStr = mapper.create().toString();
        generalMap.put(GeneralWriter.MAPPER, mapperStr);

        MapperXmlCreator mapperXml = new MapperXmlCreator();
        String mapperXmlStr = mapperXml.create(tableDefinition.getConfigInfo()).toString();
        generalMap.put(GeneralWriter.MAPPER_XML, mapperXmlStr);

        GeneralWriter writer = new GeneralWriter(generalMap, tableDefinition.getConfigInfo());
        return writer.write();
    }
}
