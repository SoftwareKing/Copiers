package com.github.trang.copiers.mapper;

import com.baidu.unbiz.easymapper.ClassMapBuilder;
import com.baidu.unbiz.easymapper.MapperFactory;
import com.baidu.unbiz.easymapper.codegen.AtoBMapping;
import com.baidu.unbiz.easymapper.transformer.Transformer;

/**
 * EasyMapper 特性支持
 *
 * @author trang
 */
public class MapperCopierSupport<F, T> {
    //自定义Mapper
    private ClassMapBuilder<F, T> builder;

    public MapperCopierSupport(Class<F> sourceClass, Class<T> targetClass) {
        this.builder = MapperFactory.getCopyByRefMapper().mapClass(sourceClass, targetClass);
    }

    /**
     * 自定义字段映射
     *
     * @param sourceField
     * @param targetField
     * @return this
     */
    public MapperCopierSupport<F, T> field(String sourceField, String targetField) {
        builder.field(sourceField, targetField);
        return this;
    }

    public <S, D> MapperCopierSupport<F, T> field(String sourceField, String targetField,
                                                  Transformer<S, D> transformer) {
        builder.field(sourceField, targetField, transformer);
        return this;
    }

    public <S, D> MapperCopierSupport<F, T> field(String sourceField, String targetField, Class<S> sourceType,
                                                  Class<D> targetType, Transformer<S, D> transformer) {
        builder.field(sourceField, targetField, sourceType, targetType, transformer);
        return this;
    }

    /**
     * 是否拷贝值为null的字段
     * true		拷贝
     * false	不拷贝，默认值
     *
     * @param mapOnNull
     * @return this
     */
    public MapperCopierSupport<F, T> nulls(boolean mapOnNull) {
        builder.mapOnNull(mapOnNull);
        return this;
    }

    /**
     * 跳过某个字段
     *
     * @param properties
     * @return this
     */
    public MapperCopierSupport<F, T> skip(String... properties) {
        builder.exclude(properties);
        return this;
    }

    /**
     * 自定义映射处理类
     *
     * @param mapping
     * @return this
     */
    public MapperCopierSupport<F, T> mapping(AtoBMapping<F, T> mapping) {
        builder.customMapping(mapping);
        return this;
    }

    /**
     * 构建执行拷贝的Copier
     *
     * @return copier
     */
    public MapperCopier<F, T> register() {
        return new MapperCopier<>(builder);
    }
}
