package com.alibaba.nacos.plugin.datasource.impl.kingbase8;

import com.alibaba.nacos.common.utils.CollectionUtils;
import com.alibaba.nacos.plugin.datasource.constants.DataSourceConstant;
import com.alibaba.nacos.plugin.datasource.constants.FieldConstant;
import com.alibaba.nacos.plugin.datasource.mapper.ConfigInfoAggrMapper;
import com.alibaba.nacos.plugin.datasource.model.MapperContext;
import com.alibaba.nacos.plugin.datasource.model.MapperResult;

import java.util.List;

/**
 * The mysql implementation of ConfigInfoAggrMapper.
 *
 * @author kqq
 **/
public class ConfigInfoAggrMapperByKingbase extends AbstractMapperByKingbase8 implements ConfigInfoAggrMapper {
    
    @Override
    public MapperResult findConfigInfoAggrByPageFetchRows(MapperContext context) {
        int startRow =  context.getStartRow();
        int pageSize =  context.getPageSize();
        String dataId = (String) context.getWhereParameter(FieldConstant.DATA_ID);
        String groupId = (String) context.getWhereParameter(FieldConstant.GROUP_ID);
        String tenantId = (String) context.getWhereParameter(FieldConstant.TENANT_ID);
        
        String sql =
                "SELECT data_id,group_id,tenant_id,datum_id,app_name,content FROM config_info_aggr WHERE data_id= ? AND "
                        + "group_id= ? AND tenant_id= ? ORDER BY datum_id LIMIT " + startRow + "," + pageSize;
        List<Object> paramList = CollectionUtils.list(dataId, groupId, tenantId);
        return new MapperResult(sql, paramList);
    }
    
    @Override
    public String getDataSource() {
        return DataSourceConstant.KINGBASE;
    }
}
