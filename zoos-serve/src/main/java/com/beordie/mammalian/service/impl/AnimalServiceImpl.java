package com.beordie.mammalian.service.impl;

import com.beordie.mammalian.entity.Animal;
import com.beordie.mammalian.mapper.AnimalMapper;
import com.beordie.mammalian.service.IAnimalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author coffeemao
 * @since 2023-03-01
 */
@Service
public class AnimalServiceImpl extends ServiceImpl<AnimalMapper, Animal> implements IAnimalService {

}
