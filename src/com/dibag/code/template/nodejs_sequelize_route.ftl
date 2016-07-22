var models  = require('../models');
var express = require('express');
var router = express.Router();

/* GET listing. */
router.get('/', function(req, res, next) {
  var pagesize = req.query.pagesize || 20, pageno = req.query.pageno || 1;
  pagesize = parseInt(pagesize);
  var _offset = parseInt(pagesize * (pageno - 1 > 0 ? pageno - 1 : 0));
  models.${table.name}.findAndCountAll({
    'limit': pagesize,                      // 每页多少条
    'offset': _offset  // 跳过多少条
  }).then(function(data) {
    res.render('${table.name}s_list', {
      title: '用户列表',
      list: data.rows,
      count:data.count
    });
  });

});

/* GET list listing. */
router.get('/list', function(req, res, next) {
  var pagesize = req.query.pagesize || 20, pageno = req.query.pageno || 1;
  pagesize = parseInt(pagesize);
  var _offset = parseInt(pagesize * (pageno - 1 > 0 ? pageno - 1 : 0));
  models.${table.name}.findAll({
    'limit': pagesize,                      // 每页多少条
    'offset': _offset  // 跳过多少条
  }).then(function(data) {
    res.render('${table.name}s_list', {
      title: '用户列表',
      list: data
    });
  });

});

/**
 * 创建
 */
router.get('/create', function(req, res) {
  res.render('${table.name}s_create', { title: '用户列表',data:[] });
});

/**
 * post保存新增数据
 */
router.post('/create', function(req, res) {
  models.${table.name}.create({
    name: req.body.name
  }).then(function() {
    res.redirect('/${table.name}');
  });
});

/**
 * 更新
 */
router.get('/:id/update', function(req, res) {
  models.${table.name}.findById(req.params.id).then(function (users) {
    res.render('${table.name}s_edit', { title: '用户编辑',data:users });
  });
});

/**
 * 更新
 */
router.post('/update', function (req, res) {
  console.log(req.body);
  models.${table.name}.update({
    title: req.body.title
  }).then(function() {
    res.redirect('/${table.name}');
  });
});

/**
 * 删除
 */
router.get('/:id/delete', function(req, res) {
  models.${table.name}.destroy({
    'where': {'id': req.params.id}
  }).then(function(){
    res.redirect('/${table.name}');
  });

});

module.exports = router;
