/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.dubbo.config.spring.context.annotation;

import com.alibaba.dubbo.config.AbstractConfig;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Enables Dubbo components as Spring Beans, equals
 * {@link DubboComponentScan} and {@link EnableDubboConfig} combination.
 * <p>
 * Note : {@link EnableDubbo} must base on Spring Framework 4.2 and above
 *
 * @see DubboComponentScan
 * @see EnableDubboConfig
 * @since 2.5.8
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
// 然后进 这个注解
// EnableDubboConfig
// 1、往spring容器里面创建标签的配置类
// 2、会对spring容器中的标签配置类进行数据绑定
// 3、数据来源是dubbo.properties ,也就是Environment对象中拿值
@EnableDubboConfig
// dubbo组件扫描
// 核心事情：
// 就是把  @Service（ServiceAnnotationBeanPostProcessor）
//     和  @Reference（ReferenceAnnotationBeanPostProcessor）  这俩注解标识的类注册到spring容器中
@DubboComponentScan
public @interface EnableDubbo {

    /**
     * Base packages to scan for annotated @Service classes.
     * <p>
     * Use {@link #scanBasePackageClasses()} for a type-safe alternative to String-based
     * package names.
     *
     * @return the base packages to scan
     * @see DubboComponentScan#basePackages()
     */
    @AliasFor(annotation = DubboComponentScan.class, attribute = "basePackages")
    String[] scanBasePackages() default {};

    /**
     * Type-safe alternative to {@link #scanBasePackages()} for specifying the packages to
     * scan for annotated @Service classes. The package of each class specified will be
     * scanned.
     *
     * @return classes from the base packages to scan
     * @see DubboComponentScan#basePackageClasses
     */
    @AliasFor(annotation = DubboComponentScan.class, attribute = "basePackageClasses")
    Class<?>[] scanBasePackageClasses() default {};


    /**
     * It indicates whether {@link AbstractConfig} binding to multiple Spring Beans.
     *
     * @return the default value is <code>false</code>
     * @see EnableDubboConfig#multiple()
     */
    @AliasFor(annotation = EnableDubboConfig.class, attribute = "multiple")
    boolean multipleConfig() default true;

}
